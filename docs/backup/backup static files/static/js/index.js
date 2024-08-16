
let isClickedCreateNew = false;
function createAccount(event){

    const button = document.querySelector('#create-new-button-account');
    const container = document.querySelector('#new-account-container');
    const close = document.querySelector('#close');



    if(button.contains(event.target) || container.contains(event.target)){

        if(container.contains(event.target)){

            if(close.contains(event.target)){
                closeButton();
                return;
            }

            showModal();
            return;
        }

        if(isClickedCreateNew){
            closeButton();
            return;
        }
        showModal();
    }else{
        closeButton();
    }

}

document.addEventListener('click',createAccount);

//Close Modal
function closeButton(){
    document.getElementById('overall').classList.remove('overlayOverAll');
    document.getElementById('new-account-container').style.display = 'none';
    isClickedCreateNew = false;
}
//Show modal
function showModal(){
    isClickedCreateNew = true;
    document.getElementById('overall').classList.add('overlayOverAll');
    document.getElementById('new-account-container').style.display = 'block';
}


function handleLogin(response){

    if(response.length > 0){
        alert("Login Failed!\n\n"+response);
        
    }

    // if(response === 'no')
    //     alert(`The username '${username}' does not exist. Please check the spelling or try a different username.`)
    // else if(response === 'wrong')
    //     alert('Wrong password. Please try again.');
}

function handleLogoutMessage(logout){
    if(logout !== ''){
        alert(logout);
    }

    console.log(logout);

}


function handleCreateAccount(message){
    if(message === 'success'){
        alert('Great job! Your account was created successfully.');
    }else if(message.includes('email') || message.includes('username') || message.length > 7){
        alert(message);
    }

}

