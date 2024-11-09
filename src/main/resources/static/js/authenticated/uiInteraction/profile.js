export const loadProfile = async (userResponse) =>{
    const fullName = `${userResponse.firstName} ${userResponse.lastName}`;
    document.querySelectorAll('.profile-full-name').forEach(name => {
        name.textContent = `${fullName}`;
    });
    document.querySelectorAll('.profile-modal-username').forEach(anchor =>{
        anchor.setAttribute('href',`/profile/${userResponse.username}`);
    });

}

export const showProfileModal = () =>{
    document.querySelector('#profile-modal').style.display = 'flex';
}

export const hideProfileModal = () =>{
    document.querySelector('#profile-modal').style.display = 'none';
}