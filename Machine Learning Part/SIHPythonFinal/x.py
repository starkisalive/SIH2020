
import mysql.connector

def getvalue():
    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="test"
    )

    mycursor = mydb.cursor()
    sql1 = "SELECT * FROM collect2 order by date desc"
    mycursor.execute(sql1)
    myresult = mycursor.fetchone()
    idd = myresult[0]
    print(idd)

    return idd

getvalue()
