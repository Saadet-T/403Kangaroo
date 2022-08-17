# 403Kangaroo
403/401 bypass tool via certain headers and values (headers.txt,values.txt). 
When you add .jar to Burp Extender it will open and write 2 txt files in Burp Suites folder. If you wish you can add values and headers as you want in those txt files  , for the later requests 403 Kangaroo will add the values you want in requests too.

This extension basically take the requests sent from proxy tab of Burp and if the request returns 401 or 403 then it takes all of the headers from headers.txt and in a for loop it will take one of the values from values.txt one at a time. 

![image](https://user-images.githubusercontent.com/68515706/184891102-1eeb99df-7b1d-4993-becd-2f5ae4ffea82.png)

![image](https://user-images.githubusercontent.com/68515706/184891166-796d11d3-ef90-4d5b-bbfa-6795c83cf65b.png)
 
 
 It will print out whether the target got bypassed or not.
 
 
![image](https://user-images.githubusercontent.com/68515706/184893331-94704641-090d-4af5-8ec8-61869cba1d12.png)

When you open the Burp Suite again you will see an output like this;

![image](https://user-images.githubusercontent.com/68515706/185059985-f3cf023a-7958-4830-b7f8-29ca606a7991.png)


If you already used the extension and if the headers.txt is from extension then you don't need to worry about it but if it is your first time using this extension and if you are not the one who copy the headers and values txt files for this extension to work ; then you need to transfer the file in order to extension work properly.

There is 2 main problems which may occur and you can get over them with this;

1.You already have an headers.txt or values.txt in path which extension is going to use then you need to move this files in order to extension can write this directory.

2.There can be something goes on with the write permisson of the extension then it will give you a path to copy headers.txt and values.txt in given error.


(I know that this GUI sucks but also coding a GUI sucks too)
