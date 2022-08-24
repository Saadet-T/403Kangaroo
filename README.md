# 403Kangaroo
403/401 bypass tool via certain headers and values (headers.txt,values.txt). When you load the extension for the first time it will open a headers.txt file and write the given headers in code to txt file and does the same thing for the values with values.txt file. So if you want to add another value or header you can add it to txt files and from then extension will include that values and headers in requests too.

USAGE;

First of all you need to send request from your proxy or repeater tab to extension. And then you need to forward or send(It depends on whether you use proxy or repeater) the request from burp suite because the extension won't be able to catch the request unless it is sent. 

![image](https://user-images.githubusercontent.com/68515706/186407101-dfa0f991-66d7-4f4c-bbc8-d133cc87fc5e.png)

![image](https://user-images.githubusercontent.com/68515706/186407201-9a072201-1648-4126-88b8-13366798b6ba.png)

![image](https://user-images.githubusercontent.com/68515706/186407591-2706dd8d-b96a-4d64-a759-3358900fb95b.png)


After that the extension will take the request and send it again to confirm whether the response code (403 || 401) or not. Then It will add the headers and values and send a request for each value .


![image](https://user-images.githubusercontent.com/68515706/186397972-0b3cbc53-096c-441d-bee9-927a4a4c9020.png)

![image](https://user-images.githubusercontent.com/68515706/186397886-62d88bc4-54f2-442c-bdfa-d12d5dd4a7b8.png)
 
 
 It will print out whether the target got bypassed or not.
 
 
![image](https://user-images.githubusercontent.com/68515706/184893331-94704641-090d-4af5-8ec8-61869cba1d12.png)

KNOWN ISSUES;

When you open the Burp Suite again you will see an output like this (or when you unload and reload the extension) ;

![image](https://user-images.githubusercontent.com/68515706/185059985-f3cf023a-7958-4830-b7f8-29ca606a7991.png)


If you already used the extension and if the headers.txt is from extension then you don't need to worry about it but if it is your first time using this extension and if you are not the one who copy the headers and values txt files for this extension to work ; then you need to transfer the file in order to extension work properly.

There is 2 main problems which may occur and you can get over them with these;

1.You already have an headers.txt or values.txt in path which extension is going to use then you need to move this files in order to extension can write this directory.

2.There can be something goes on with the write permisson of the extension then it will give you a path to copy headers.txt and values.txt in given error.


(I know that this GUI sucks but also coding a GUI sucks too, I will work on it sometime but honestly I don't know when )
