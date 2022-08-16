package burp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
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
import com.kitfox.svg.Path;

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
	Font bigFont = new Font();

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		this.callbacks = callbacks;
		this.helpers = callbacks.getHelpers();
		this.callbacks.setExtensionName("403Kangaroo");
		this.callbacks.registerHttpListener(this);
		this.debug = new PrintWriter(callbacks.getStdout(), true);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panel = new JPanel();
				label = new JLabel();
				textArea = new JTextArea(20,2);
				File fileHeaders = new File("headers.txt");
				String pathHeaders = fileHeaders.getAbsolutePath();
				debug.println(pathHeaders);
			GroupLayout layout = new GroupLayout(panel);
				panel.setLayout(layout);
				layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(textArea));
					
			layout.setVerticalGroup(layout.createSequentialGroup().addComponent(textArea));


				callbacks.customizeUiComponent(panel);
				callbacks.addSuiteTab(BurpExtender.this);

			}

		});
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
						debug.println(newPathValues);
						List<String> allValues = Files.readAllLines(Paths.get(newPathValues));
						String[] valueArray = new String[allValues.size() * 2];
						allValues.toArray(valueArray);
						for (int i = 0; i <= (allLines.size()); i++) {
							for (int j = 0; j <= (allValues.size()); j++) {
								IRequestInfo request = this.helpers.analyzeRequest(messageInfo.getHttpService(),
										messageInfo.getRequest());
								List<String> newHeaders = request.getHeaders();
								newHeaders.add(6, headerArray[i] + ":" + valueArray[j]);
								Thread.sleep(250);
								byte[] newRequest = this.helpers.buildHttpMessage(newHeaders, null);
								messageInfo.setRequest(newRequest);
								newHeaders.remove(headerArray[i] + ":" + valueArray[j]);
								byte[] newRequesta = this.helpers.buildHttpMessage(newHeaders, null);
								debug.println(new String(newRequest));
								messageInfo.setRequest(newRequesta);
								IHttpRequestResponse resp = callbacks.makeHttpRequest(messageInfo.getHttpService(),
										newRequest);
								byte[] readme_response = resp.getResponse();
								String response = new String(readme_response);
								String[] responseArray = response.split("\n");
								if (responseArray[0].contains("200")) {
									textArea.append(url + " || This header =>" + headerArray[i] + ":" + valueArray[j]
											+ " Returns 200!!\n");
									a = a + 1;

								}

							}
						}
						if (a == 0) {
							textArea.append(url + " || This header and value failed\n");
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
