from tensorflow.keras.applications.vgg16 import VGG16
from tensorflow.keras.preprocessing import image
from tensorflow.keras.applications.vgg16 import preprocess_input, decode_predictions
import numpy as np


model = VGG16(weights='imagenet')
img_path = 'car.png'
img = image.load_img(img_path, target_size=(224, 224))
x = image.img_to_array(img)
x = np.expand_dims(x, axis=0)
x = preprocess_input(x)

preds = model.predict(x)
print('Вывод расчётов сети распознавания:\n', decode_predictions(preds, top=3)[0])
#moscito net   plane
#toilet paper  car