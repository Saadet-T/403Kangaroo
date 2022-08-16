package burp;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.kitfox.svg.Font;

public class BurpExtender implements IBurpExtender, IHttpListener, ITab {

	private IBurpExtenderCallbacks callbacks;
	private IExtensionHelpers helpers;
	private IHttpRequestResponse message;
	private PrintWriter debug;
	private JPanel panel;
	private JLabel label;
	private JButton Button;
	private JTextArea textArea, textArea2,textArea3;
	private JMenuItem item;
	private List<JMenuItem> menu;
	public List<String> deneme = new ArrayList<String>();;
	boolean atama = false;


	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		this.callbacks = callbacks;
		this.helpers = callbacks.getHelpers();
		this.callbacks.setExtensionName("403Kangaroo");
		this.callbacks.registerHttpListener(this);
		this.debug = new PrintWriter(callbacks.getStdout(), true);
		String[] headerswrite = {"X-Forwarded",
	            "X-Forwarded-By",
	            "X-Forwarded-For",
	            "X-Forwarded-For-Original",
	            "X-Forwarder-For",
	            "X-Forwarded-Server",
	            "X-Forward-For",
	            "Forwarded-For",
	            "Forwarded-For-Ip",
	            "X-Custom-IP-Authorization",
	            "X-Originating-IP",
	            "X-Remote-IP",
	            "X-Remote-Addr",
	            "X-Trusted-IP",
	            "X-Requested-By",
	            "X-Requested-For",
	            "X-Host",
	            "X-Original-Remote-Addr",
	            "X-Originating-IP",
	            "X-Real-Ip",
	            "X-True-IP",
	            "Client-IP",
	            "Redirect",
	            "Referer",
	            "X-Client-IP",
	            "X-True-IP"};
		String[] valueswrite = {"127.0.0.1",
	            "0.0.0.0",
	            "localhost"};

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panel = new JPanel();
				label = new JLabel();
				textArea = new JTextArea(20,2);
				
				GroupLayout layout = new GroupLayout(panel);
					panel.setLayout(layout);
					layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(textArea));
						
				layout.setVerticalGroup(layout.createSequentialGroup().addComponent(textArea));


					callbacks.customizeUiComponent(panel);
					callbacks.addSuiteTab(BurpExtender.this);
				File fileHeaders = new File("headers.txt");
				String pathHeaders = fileHeaders.getAbsolutePath();
				if(fileHeaders.exists()==true) {
					debug.println(("If you already used this extension you can ignore this error,\n however if this is your first time using;you have a headers.txt in "+pathHeaders+".\nYou need to transfer this file in another location for this extension to work."));
				}
				if(fileHeaders.exists()==false) {
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(pathHeaders));
						writer.write(headerswrite[0]+"\n");
					for(int i=1;i<(headerswrite.length);i++) {
				
						writer.append(headerswrite[i]+"\n");
						//debug.println(headerswrite[i]);
						
					} 	
					writer.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					File filevalues = new File("values.txt");
					String pathvalues = filevalues.getAbsolutePath();
					if(filevalues.exists()==true) {
						debug.println(("\n\nIf you already used this extension you can ignore this error,\n however if this is your first time using;you have a headers.txt in "+pathvalues+".\nYou need to transfer this file in another location for this extension to work."));
					}
					if(filevalues.exists()==false) {
						try {
							BufferedWriter writer = new BufferedWriter(new FileWriter(pathvalues));
							writer.write(valueswrite[0]+"\n");
						for(int i=1;i<(valueswrite.length);i++) {
					
							writer.append(valueswrite[i]+"\n");
							//debug.println(valueswrite[i]);
							
						} 	
						writer.close();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


				}
				

			}

			}});
	}


	

	
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		URL url = messageInfo.getUrl();
		url.toString();
		if (messageIsRequest) {
			if (this.callbacks.TOOL_PROXY == toolFlag) {
				IRequestInfo requestMother = this.helpers.analyzeRequest(messageInfo.getHttpService(),
						messageInfo.getRequest());
				List<String> motherHeaders = requestMother.getHeaders();
				byte[] motherRequest = this.helpers.buildHttpMessage(motherHeaders, null);
				messageInfo.setRequest(motherRequest);
				IHttpRequestResponse motherResp = callbacks.makeHttpRequest(messageInfo.getHttpService(),
						motherRequest);
				byte[] mother_response = motherResp.getResponse();
				String motheResponse = new String(mother_response);
				String[] responseArrayMother = motheResponse.split("\n");

				if (responseArrayMother[0].contains("403") || responseArrayMother[0].contains("401")) {
					
					try {

						int a = 0;
						File fileHeaders = new File("headers.txt");
						String pathHeaders = fileHeaders.getAbsolutePath();
						String newPath = pathHeaders.replaceAll("\\\\", "\\\\\\\\");
						//debug.println(newPath);
						List<String> allLines = Files.readAllLines(Paths.get(newPath));
						String[] headerArray = new String[allLines.size() * 2];
						allLines.toArray(headerArray);
						File fileValues = new File("values.txt");
						String pathValues = fileValues.getAbsolutePath();
						String newPathValues = pathValues.replaceAll("\\\\", "\\\\\\\\");
						//debug.println(newPathValues);
						List<String> allValues = Files.readAllLines(Paths.get(newPathValues));
						String[] valueArray = new String[allValues.size() * 2];
						allValues.toArray(valueArray);
						
							for (int j = 0; j <= (allValues.size()); j++) {
								IRequestInfo request = this.helpers.analyzeRequest(messageInfo.getHttpService(),
										messageInfo.getRequest());
								List<String> newHeaders = request.getHeaders();
								   for (int i=0;i<=(allLines.size());i++) {
							            newHeaders.add(6,headerArray[i]+":"+valueArray[j]);
							            }
								Thread.sleep(250);
								//debug.println(newHeaders);
								byte[] newRequest = this.helpers.buildHttpMessage(newHeaders, null);
								messageInfo.setRequest(newRequest);
								for (int i=0;i<=(allLines.size());i++) {
									newHeaders.remove(headerArray[i] + ":" + valueArray[j]);
						            }
								byte[] newRequesta = this.helpers.buildHttpMessage(newHeaders, null);
								//debug.println(new String(newRequest));
								messageInfo.setRequest(newRequesta);
								IHttpRequestResponse resp = callbacks.makeHttpRequest(messageInfo.getHttpService(),
										newRequest);
								byte[] readme_response = resp.getResponse();
								String response = new String(readme_response);
								String[] responseArray = response.split("\n");
								if (responseArray[0].contains("200")) {
									textArea.append(url + " || This header =>"  + ":" + valueArray[j]
											+ " Returns 200!!\n");
									a = a + 1;

								}

							}
						
						if (a == 0) {
							textArea.append(url + " ||  header and value failed\n");
						}
					}

					catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			}
	}

	@Override
	public String getTabCaption() {

		return "403 Kangaroo";
	}

	@Override
	public Component getUiComponent() {

		return this.panel;
	}


}