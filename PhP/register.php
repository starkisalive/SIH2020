<?php
$user = $_GET['user'];
$email = $_GET['email'];
$pass = $_GET['pass'];
require_once 'connect.php';
$sql1 = "SELECT * from data WHERE user = '$user'";
$sql = "INSERT INTO data (user, email, pass) VALUES ('$user','$email','$pass')";

$r = mysqli_query($conn,$sql1);
$row = mysqli_num_rows($r);
$result = array();
//echo $row
	if($row >= 1){
        	$result["sts"]="-1";
        	$result["message"]="userexist";
        	echo  json_encode($result);
        
	}else{
        	$r = mysqli_query($conn,$sql);
        	if($r){
                	$result["sts"]="1";
               		$result["message"]="success";
                	echo json_encode($result);
    
        	}else{
                	$result["sts"]="0";
                	$result["message"]="error";

                	echo json_encode($result);
    
    		}
	                    
	}
?>
