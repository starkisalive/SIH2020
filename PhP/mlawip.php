<?php
$conn=mysqli_connect("localhost","root","","sih");
// Check connection
if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

//$email='swastikpradhan.rubul@gmail.com';
//$status='Contractor assigned';
$data=mysqli_query($conn,"SELECT * FROM citizen_data WHERE status='Work Started'");
//$sql="UPDATE collect2 SET status='$status' WHERE id='$id'";

while($result=mysqli_fetch_array($data)){
$r = $result["id"];
$l = $result["land"];
$d = $result["deadline"];
//echo $result["id"];
echo "Complaint id : $r";
echo"<br>";
echo "Location : $l";
echo"<br>";
echo "Deadline : $d" ;
echo"||";
}

//mysqli_close($conn);
?>