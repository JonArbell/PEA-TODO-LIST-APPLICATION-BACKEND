export const menuToggle = () =>{

    const hamburgerButton = document.querySelector('#check');

    hamburgerButton.addEventListener('change',()=>{

        const menuPage = document.querySelector('#mobile-menu');
        const mainHead = document.querySelector('#main-head');
        const mainBody = document.querySelector('#main-body');
        const createNewItemButton = document.querySelector('#create-new-item-button');

        if(hamburgerButton.checked){
            menuPage.style.display = 'block';
            mainHead.style.display = 'none';
            mainBody.style.display = 'none';
            createNewItemButton.style.display = 'none';
        }
        else{
            menuPage.style.display = 'none';
            mainHead.style.display = 'flex';
            mainBody.style.display = 'block';
            createNewItemButton.style.display = 'block';
        }
            
    });
}