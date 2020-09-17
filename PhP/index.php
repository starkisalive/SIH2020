<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Change Your Password</title>
</head>
<body>
<div class="card">
            <h5 class="card-header">Welcome To Gaddhe</h5>
            <div class="card-body" style="width: 500px; margin-left: 0px; margin-top: 0px; align-self: center;">
            <form method="post">
                <div class="form-group row">
                    <label for="inputPassword" class="w-25 p-3">Password</label>
                    <div style="margin-top: 20px;">
                        <input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="w-25 p-3">Confirm Password</label>
                    <div style="margin-top: 20px;">
                        <input type="password" class="form-control" id="cpass" name="cpass" placeholder="Password">
                    </div>
                </div>
                <input onclick="myFunction()" class="btn btn-primary" type="submit" value="Submit">
            </form>
            </div>
</div>
<?php

    error_reporting(E_ALL & ~E_NOTICE);
    $u_name = $_GET["uname"];
    $pass = $_POST["pass"];
    $hashed_password = hash('sha512',$pass);
    $cpass = $_POST["cpass"];

    // echo $u_name;
    // echo $pass;
    // echo $cpass;

    $link = mysqli_connect("localhost", "root", "", "sih");
    if($link === false){
        die("ERROR: Could not connect. " . mysqli_connect_error());
    }
    else{
        if($pass === $cpass){
            
        $sql = "UPDATE contractor SET cpassword='$pass' WHERE cemail='$u_name'";
        if(mysqli_query($link, $sql)){
            ?>
            <script>
                function myFunction() {
                    var pass = document.getElementById("pass").value;
                    var cpass = document.getElementById("cpass").value;
                    if(pass === cpass){
                        alert("Password Changed Sucessfully");
                    }
                    else{
                        alert("Password Wrong");
                    }
                   
                }
            </script>

           <?php 
        } else{
            echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
        }

        }
        else{
            
        }
        
    }
    

?>
</body>
</html>
