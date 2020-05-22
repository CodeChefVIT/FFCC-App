import textblob_p
import nltk_p
import pickle
from flask import Flask, request, jsonify
with open('textblob_model.pkl','rb') as P:
  model_1 = pickle.load(P)

print(model_1.predict("She is a good teacher"))

with open('nltk_model.pkl','rb') as P:
  model_2 = pickle.load(P)

print(model_2.predict("She is a good teacher"))



app = Flask(__name__)
@app.route('/<string:review>', methods = ["GET"])
@app.route('/')
def index(review = None):
    if review is not None:
        average = (model_1.predict(review) + model_2.predict(review))/2
        file = [{review:average}
                ]
        return jsonify(file)
    else:
        return "GET"


if __name__ == "__main__":
    app.run(debug = True)
