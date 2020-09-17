<?php
$user = $_GET['user'];
require_once('connect.php');
$sql = "SELECT * FROM citizen_data where user = '$user' order by id desc";
$r = mysqli_query($conn,$sql);
$result = array();
while($res = mysqli_fetch_array($r)){
array_push($result,array(
"id"=>$res['id'],
"sts"=>$res['status'],
"date"=>$res['date'],
"near"=>$res['land'],
"ImagePath"=>$res['url']
)
);
}
echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>
