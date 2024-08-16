<!DOCTYPE html>
<html>
    <head>
        <title>Practice lang </title>

        <style>
            table{

                height: 400px;
                width: 310px;
            }

            tr{
                position: relative;
                margin: 0px;
                height: 10px;
            }

            th{
                border: 2.5px solid black;
                height: 5px;
            }

            h3{
                text-align: center;
                margin: 0px;
            }
            h4{
                text-align: center;
                margin: 0px;
            }
            td{
                border: solid 2.5px black;
                width:100px ;
            }
            
            .last-td{
                border: 0px;
                width: 290px;
                height: 75px;
            }

            textarea{
                resize: none;
                border: 2.5px solid black;
            }

            button{
                position: absolute;
                top: 12px;
                padding: 15px;
                
            }

            .clear{
                right: 50px;
            }

            .submit{
                left: 50px;
                
            }

            .buttons{
                position: relative;
                border: 2.5px solid black;
                margin: 3px;
                width: 345px;
                height: 75px;
            }

        </style>

    </head>

    <body>

        <div class="contact-form">
            <h1>1) - Contact Us Form</h1>
        </div>

        <form>
            <table>
                <tr>
                    <th colspan="2">
                        <h3>Send us an email</h3>
                    </th>
                </tr>
    
                <tr>
                    <td>Subject:*</td>
                    <td>
                        <select class="user-input">
                            <option>Data Structures and Algorithm</option>
                            <option>Computer Programming 2</option>
                            <option>Object Oriented Programming</option>
                            <option>Web App Programming</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Your name:*</td>
                    <td>
                        <input type="text" class="user-input">
                    </td>
                </tr>
    
                <tr>
                    <td>Title:*</td>
                    <td>
                        <input type="text" class="user-input">
                    </td>
                </tr>
    
                <tr>
                    <td>Company:*</td>
                    <td>
                        <input type="text" class="user-input">
                    </td>
                </tr>
    
                <tr>
                    <td>Phone number:*</td>
                    <td>
                        <input type="text" class="user-input">
                    </td>
                </tr>
    
                <tr>
                    <td>Email address:*</td>
                    <td>
                        <input type="text" class="user-input">
                    </td>
                </tr>
    
                <tr>
                    <th colspan="2">
                        <h4>Message:*</h4>
                        
                    </th>
    
                </tr>
                
                <tr>
                    <td class="last-td" colspan="2">
                        <textarea rows="7" cols="45"></textarea>
                    </td>
                    
                </tr>
    
            </table>

            <div class="buttons">
                <button class="submit">Submit</button>
                <button class="clear">Clear</button>
            </div>

        </form>

        
            
            

        

    </body>

</html>