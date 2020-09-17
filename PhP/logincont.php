<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $user = $_POST['user'];
    $pass = $_POST['pass'];

    require_once 'connect.php';

    $sql = "SELECT * FROM contractor WHERE cemail='$user' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( $pass == $row['cpassword'])  {
            
            
            $index['user'] = $row['cname'];
            $index['email'] = $row['cemail'];

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            

        }

    }

}

?>
