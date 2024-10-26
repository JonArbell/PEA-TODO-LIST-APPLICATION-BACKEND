export const menuToggle = () =>{

    const hamburgerButton = document.querySelector('#check');

    hamburgerButton.addEventListener('change',()=>{

        const menuPage = document.querySelector('#mobile-menu');
        const mainHead = document.querySelector('#main-head');
        const mainBody = document.querySelector('#main-body');
        const createNewItemButton = document.querySelector('#create-new-item-button');
        const hr = document.querySelector('#hr-mobile-menu-remove');
        if(hamburgerButton.checked){

            if(mainHead) 
                mainHead.style.display = 'none';
            
            if(mainBody) 
                mainBody.style.display = 'none';

            if(hr) 
                hr.style.display = 'none';

            if(createNewItemButton) 
                createNewItemButton.style.display = 'none';

            menuPage.style.display = 'block';

        }
        else{

            if(mainHead) 
                mainHead.style.display = 'flex';

            if(mainBody) 
                mainBody.style.display = 'block';

            if(hr) 
                hr.style.display = 'block';

            if(createNewItemButton) 
                createNewItemButton.style.display = 'block';

            menuPage.style.display = 'none';
            
        }

    });

    
}