# 403Kangaroo
403/401 bypass tool via certain headers and values (headers.txt,values.txt). 
When you add .jar to Burp Extender it will open and write 2 txt files in Burp Suites folder. If you wish you can add values and headers as you want in those txt files  , for the later requests 403 Kangaroo will add the values you want in requests too.

First of all you need to send request from your proxy or repeater tab to extension. And then you need to forward the request from burp suite because until the request was sent from burp suite the extension can't catch the request. 
![image](https://user-images.githubusercontent.com/68515706/186393150-459518be-010d-4302-bc5c-2beecefb6dc5.png)

![image](https://user-images.githubusercontent.com/68515706/186393276-1da105b2-1556-4967-a82b-921c872632c2.png)

After that the extension will take the request send it again to confirm whether the response code (403 || 401) or not. Then It will add the headers from headers.txt .

When you load the extension for the first time it will open a headers.txt file and write the headers in there and does the same thing for values. So if you want to add another value or header you can add it to txt files and from then extension will include that values and headers too.

![image](https://user-images.githubusercontent.com/68515706/186393455-983bfcd0-c4d7-47b4-8f38-eec98e2f9fad.png)
![image](https://user-images.githubusercontent.com/68515706/186393386-09050b7a-40e6-4c40-ab21-18951aad95bb.png)
 
 
 It will print out whether the target got bypassed or not.
 
 
![image](https://user-images.githubusercontent.com/68515706/184893331-94704641-090d-4af5-8ec8-61869cba1d12.png)



When you open the Burp Suite again you will see an output like this;

![image](https://user-images.githubusercontent.com/68515706/185059985-f3cf023a-7958-4830-b7f8-29ca606a7991.png)


If you already used the extension and if the headers.txt is from extension then you don't need to worry about it but if it is your first time using this extension and if you are not the one who copy the headers and values txt files for this extension to work ; then you need to transfer the file in order to extension work properly.

There is 2 main problems which may occur and you can get over them with this;

1.You already have an headers.txt or values.txt in path which extension is going to use then you need to move this files in order to extension can write this directory.

2.There can be something goes on with the write permisson of the extension then it will give you a path to copy headers.txt and values.txt in given error.


(I know that this GUI sucks but also coding a GUI sucks too)
