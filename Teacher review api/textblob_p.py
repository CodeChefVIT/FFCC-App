class Model:
  def __init__(self,tex = None):
    self.tex = None

  def predict(self, text):
    from textblob import TextBlob
    sent = TextBlob(text)
    rates = sent.polarity
    return rates + 2.0

model = Model()
import pickle
with open('textblob_model.pkl', 'wb') as pickle_obj:
  pickle.dump(model, pickle_obj)
