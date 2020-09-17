import requests
import mysql.connector






def imageload(num):
    # """write sql link query statement here assignit to url"""
    url1 = "http://"

    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="sih"
    )

    mycursor = mydb.cursor()

    mycursor.execute("SELECT * FROM citizen_data order by id desc")

    myresult = mycursor.fetchone()
    idd = myresult[0]
    url1 = url1 + myresult[8]  
  

    # url=["http://padma.soumit.tech/image/rintu1594907373cropped6678980304102778395.jpg","http://padma.soumit.tech/image/pralipta1593978411cropped5765343120082233727.jpg"]
    response = requests.get(url1)
    file = open("sample_image"+str(num)+".jpg", "wb")
    file.write(response.content)
    file.close()
    filename="sample_image"+str(num)+".jpg"
    # num = num + 1
    return filename



