simple API which gives values of rating from 1 to 3  for any teacher ratings

STEP 1
install python to run the api(mostly you will have it)
and these are the pip dependancies
pandas==0.25.1
numpy==1.18.1
Flask==1.1.1
nltk==3.5
textblob==0.15.3

STEP 2
Once done installing the dependancies, open cmd in the same directory and type "python api.py"
[NOTE: IF this does'nt work, you need to add python to the PATH environment variable 
(you can easily find it out in google)]

STEP 3
if the cmd command works, the api server starts running through the localhost.
Then just send a GET request.
FOR reference check out : https://www.twilio.com/blog/2017/08/http-requests-in-node-js.html
GET request:Whole localhost url + / + teacher review
Example => http://127.0.0.1:5000/She is a great teacher.

STEP 4
Done. :)


API Owner : Jofin F Archbald
Authors : Adithya Beri and Jofin F Archbald

