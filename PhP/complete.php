<?php
$sts = $_GET['sts'];
$email = $_GET['email'];
$sts2 = "Work Started";
require_once('connect.php');
$sql = "SELECT * FROM citizen_data where status = '$sts' and contr = '$email' order by id desc ";
$r = mysqli_query($conn,$sql);
$result = array();
while($res = mysqli_fetch_array($r)){
array_push($result,array(
"user"=>$res['user'],
"date"=>$res['deadline'],
"near"=>$res['land'],
"id"=>$res['id'],
"lod"=>$res['lod'],
"lat"=>$res['lat'],
"lon"=>$res['lon'],
"ImagePath"=>$res['url']
)
);
}
echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>
