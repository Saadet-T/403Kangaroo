# 403Kangaroo
403/401 bypass tool via certain headers and values (headers.txt,values.txt). 
When you add .jar to Burp Extender it will open and write 2 txt files in Burp Suites folder. If you wish you can add values and headers as you want in those txt files  , for the later requests 403 Kangaroo will add the values you want in requests too.

This extension basically take the requests sent from proxy tab of Burp and if the request returns 401 or 403 then it takes all of the headers from headers.txt and in a for loop it will take one of the values from values.txt one at a time. 

![image](https://user-images.githubusercontent.com/68515706/184891102-1eeb99df-7b1d-4993-becd-2f5ae4ffea82.png)
![image](https://user-images.githubusercontent.com/68515706/184891166-796d11d3-ef90-4d5b-bbfa-6795c83cf65b.png)
 
 
 It will print out whether the target got bypassed or not.
 
 
![image](https://user-images.githubusercontent.com/68515706/184890408-a9753df7-9ae4-49c0-81b4-9fea71aa4666.png)

When you open the Burp Suite again you will see an output like this;


![image](https://user-images.githubusercontent.com/68515706/184891666-1e8af9c3-5138-4bd2-a2d4-ca42fb6a11eb.png)


If you already used the extension and the headers.txt file is from extension than you don't need to worry but if it is your first time you need to transfer the file in order for the extension work properly.

(I know that GUI sucks but also coding a GUI sucks too)
