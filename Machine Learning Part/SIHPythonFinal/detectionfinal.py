import cv2
import numpy as np

#load yolo
net = cv2.dnn.readNet("yolov2-tiny_4000.weights", "yolov2-tiny.cfg")
classes = []
with open("coco.names","r") as f:
    classes = [line.strip() for line in f.readlines()]
layer_names = net.getLayerNames()
output_layers = [layer_names[i[0] - 1] for i in net.getUnconnectedOutLayers()]

#loading image
img = cv2.imread("cbimage.jpg") #the image name goes here
print(type(img))
img = cv2.resize(img,None, fx=0.35, fy=0.35)
height, width, channels = img.shape

#detecting objects
blob = cv2.dnn.blobFromImage(img, 0.00392, (416, 416), (0, 0, 0), True, crop=False)

#print(blob)

net.setInput(blob)
outs = net.forward(output_layers)

#showing info on the screen
count = 0
for out in outs:
    for detection in out:
        scores = detection[5:]
        class_id = np.argmax(scores)
        confidence = scores[class_id]
        if confidence > 0.2:
            #object detected
            count += 1
            center_x = int(detection[0] * width)
            center_y = int(detection[1] * height)
            w = int(detection[2] * width)
            h = int(detection[3] * height)

            #cv2.circle(img, (center_x, center_y), 10, (0,255,0), 2)
            #rectangle coordinates
            x = int(center_x - w / 2)
            y = int(center_y - h / 2)

            cv2.rectangle(img, (x,y), (x+w, y+h), (0,255,0), 2)


print(count)
cv2.imshow("Image",img)
cv2.waitKey(0)
cv2.destroyAllWindows()
