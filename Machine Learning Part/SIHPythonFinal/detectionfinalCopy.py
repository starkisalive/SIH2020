localhost",
    user="root",
    password="SOUMITdas768648",
    database="test",
    buffered=True
    )
    
    

#load yolo
net = cv2.dnn.readNet("yolov2-tiny_4000.weights", "yolov2-tiny.cfg")
classes = []
with open("coco.names","r") as f:
    classes = [line.strip() for line in f.readlines()]
layer_names = net.getLayerNames()
output_layers = [layer_names[i[0] - 1] for i in net.getUnconnectedOutLayers()]
p = 0
fcnt=0
while True:

    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="SOUMITdas768648",
    database="test"
    )

    mycursor = mydb.cursor()

    mycursor.execute("SELECT * FROM collect2 order by id desc")

    myresult = mycursor.fetchall()
    # url1 = url1 + myresult[7]  


    no = len(myresult)
    if no > p:
#	print("Length:")
        print(no)
        p = no
    
    #downloading imge
        file_image=imgdwnld.imageload(fcnt)
        fcnt += 1
        #print(file_image)

        #loading image
        img = cv2.imread(file_image) #the image name/path goes here
        # cv2.imshow("Image",img)  

        #img = cv2.resize(img,None, fx=0.3, fy=0.3)
        height, width, channels = img.shape

        #detecting objects
        blob = cv2.dnn.blobFromImage(img, 0.00392, (416, 416), (0, 0, 0), True, crop=False)

        net.setInput(blob)
        outs = net.forward(output_layers)

        count= 0
        #showing info on the screen
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

	
#	print("No Of Pothole:")
        print(count)
#	if count>2 and count<7:
#		lod = 'Modrate'
#	elif count >= 7 :
#		lod = 'Major'
#	elif count>0 and count<3:
#		lod = 'Minor'
#	else:
#		lod = 'None'
        # mycursor = mydb.cursor(prepared=True)
        # sql1 = "SELECT * FROM collect2 order by date desc"
        # mycursor.execute(sql1)
        # myresult = mycursor.fetchone()
        # idd = myresult[0]
        idd = xyz.getvalue()
        #print(type(idd),idd)
        sql = "UPDATE collect2 SET lod = %s WHERE id = %s"
        val = (count,idd)
        mycursor.execute(sql,val)
        mydb.commit()
        # mydb.commit()
        # updateSQL(count)
        # write sql statement to send response i.e. 
        #comment/uncomment the next line to see the detected potholes
        #cv2.imshow("Image",img)  
        #cv2.waitKey(0)
        #cv2.destroyAllWindows()
