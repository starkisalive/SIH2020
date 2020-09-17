<?php
$conn=mysqli_connect("localhost","root","","sih");
// Check connection
if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

//$cont=$_POST["email"];
//$status=$_POST['status'];

$data=mysqli_query($conn,"SELECT * FROM citizen_data WHERE status='Assigned'");
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