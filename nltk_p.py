import numpy as np


import nltk
    
nltk.download('vader_lexicon')

from nltk.sentiment.vader import SentimentIntensityAnalyzer

sid = SentimentIntensityAnalyzer()

class MODEL:
  score = None
  def __init__(self):
    pass

  @classmethod
  def predict(cls,text):
    polarity_rating = sid.polarity_scores(text)
    rating = np.array([polarity_rating['neg'], polarity_rating['neu'], polarity_rating['pos']])
    if rating[0] > rating[2] :
      diff = rating[0] - rating[2]
      if diff <= 0.33333333:
        cls.score = 2
      else:
        cls.score = 1
    elif rating[0] < rating[2] :
      diff = rating[2] - rating[0]
      if diff <= 0.33333333:
        cls.score = 2
      else:
        cls.score = 3
    else:
      cls.score = 2
    return cls.score

obj = MODEL()
import pickle
with open('nltk_model.pkl','wb') as P:
  pickle.dump(obj,P)
