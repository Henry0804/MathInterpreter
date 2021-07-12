# Run info

Download the jar from the latest release.
Put the file in the downloads folder or for linux the home folder.
Now go to cmd (or bash) and type:
cd Downloads (or) cd ~
Now install java if you don't have it and run:
java -jar filename.jar
Now enjoy your mathinterpreter!

# MathInterpreter
 A simple math interpreter.

# How it works
Download the jar and run it, you'll see  
Calc >
appear on the screen. Type in something like 1+1*2.  
It will follow the order of operations.  
So how does a program know how to do that?  
First a lexer is used, it turns the math into simple tokens, or smaller pieces.  
For example:  
1 + 1 becomes: (1) (+) (1)  
Next this output gets turned into nodes, basically they don't do much except have a QuickParse method.  
To figure out the order of operations, here is an example of how the program does it.  
First the input passes through a parse factors function, it does this to the nodes:  
1 + 1 * 2 becomes: 1 + (1 * 2)  
The function does this to all multiply and divide nodes.  
That is about how it works, it slowly builds up generating a tree-like structure:  
1  +  1  *  2 becomes:  

---ADD  
--1--MUL  
----1---2

In order for the add to complete, it must first complete a MUL operation, or multiply.

# Work In Progress discord server:
https://discord.gg/xbBACUj5jw
