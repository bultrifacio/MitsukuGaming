
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <h1>Add a product</h1>
    <form action="FrontController">
        <fieldset>
        <legend>Product information:</legend>
        Name: <input name="name" type="text"><br>
        Quantity: <input name="quantity" type="text"><br>
        Price: <input name="price" type="text"><br>
        Cost: <input name="cost" type="text"><br>
        Description:<br>
        <textarea name="description" rows="10" cols="20"></textarea><br>
        Available: <select name="avaible">
            <option value="True">True</option>
            <option value="False">False</option>
        </select><br>
        Release date: <input name="date" type="text"><br>
        Synopsis:<br>
        <textarea name="synopsis" rows="10" cols="20"></textarea><br>
        <input name="command" value="AddProductCommand" type="submit"><br>
        </fieldset>
    </form>


</html>