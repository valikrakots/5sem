from tensorflow.keras.models import model_from_json
from tensorflow.keras.preprocessing.text import hashing_trick, text_to_word_sequence
from tensorflow.keras.preprocessing.sequence import pad_sequences
import numpy as np


json_file = open("imdb.json", "r")
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
loaded_model.load_weights("imdb.h5")

max_features = 5000
maxlen = 80

loaded_model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

file1=open("review1.txt")
review1=file1.read()
x1=hashing_trick(review1, max_features)

file2=open("review2.txt")
review2=file2.read()
x2=hashing_trick(review2, max_features)

arr = np.array([x1, x2])
arr = pad_sequences(arr, maxlen=maxlen)
y=np.array([1, 0])

scores = loaded_model.evaluate(arr, y, verbose=1)


print(loaded_model.predict(arr[:1]), y[0])
print(loaded_model.predict(arr[1:]), y[1])
