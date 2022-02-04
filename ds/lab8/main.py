import numpy as np
from tensorflow.keras.models import model_from_json
from tensorflow.keras.preprocessing import image


labels = ["airplane",
"automobile",
"bird",
"cat",
"deer",
"dog",
"frog",
"horse",
"ship",
"truck"]

labels2 = [
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9"
]


json_filename = "mnist_model2.json"
with open(json_filename, "r") as json_file:
    loaded_model_json = json_file.read()
model = model_from_json(loaded_model_json)
h7_filename = "second"
model.load_weights(h7_filename)

json_filename = "mnist_model.json"
with open(json_filename, "r") as json_file:
    loaded_model_json = json_file.read()
model2 = model_from_json(loaded_model_json)
h7_filename = "first"
model2.load_weights(h7_filename)

img_path = '3.png'
img_path2 = 'plane.jpg'
img = image.load_img(img_path, target_size=(28, 28), grayscale=True)
img2 = image.load_img(img_path2, target_size=(32, 32))

x = image.img_to_array(img)
x = 255 - x
x /= 255
x = np.expand_dims(x, axis=0)

x2 = image.img_to_array(img2)
x2 = 255 - x2
x2 /= 255
x2 = np.expand_dims(x2, axis=0)

result = model.predict(x)
for i, perc in enumerate(np.round(100 * result)[0]):
    print("%d: %d%%" % (i, perc))
print("result = %d" % np.argmax(result))


print("\n\n")
result = model2.predict(x2)
for i, perc in enumerate(np.round(100 * result)[0]):
    print("%d: %d%%" % (i, perc))
print("result = %d" % np.argmax(result))


































