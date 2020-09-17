
import mysql.connector

def getvalue():
    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="sih"
    )

    mycursor = mydb.cursor()
    sql1 = "SELECT * FROM citizen_data order by id desc"
    mycursor.execute(sql1)
    myresult = mycursor.fetchone()
    idd = myresult[0]
#    print(idd)

    return idd

getvalue()
