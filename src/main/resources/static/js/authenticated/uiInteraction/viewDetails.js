export const viewDetails = () =>{
    document.querySelector('#view-details').style.display = 'flex';
    document.querySelector('#modal-view-background').style.display = 'block';
}

export const removeDetails = () =>{
    document.querySelector('#view-details').style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}