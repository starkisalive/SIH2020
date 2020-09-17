<?php
// $url = $_GET['url'];
// $budget = $_GET['budget'];
$name = $_GET['name'];
$point = 100;
require_once('connect.php');
$sql = "SELECT * from citizen_data WHERE user = '$name' and status = 'Completed' " ;
$r = mysqli_query($conn,$sql);
$rowcount=mysqli_num_rows($r);
$rowcount = $rowcount * $point;
//echo $rowcount;
$result = array();
if($r){
    $result['status'] = "Sucess";
    $result['sts'] = $rowcount;
	echo json_encode($result);
}else{

	$result['status'] = "Unsucess";
	echo json_encode($result);
}
mysqli_close($conn);
?>
