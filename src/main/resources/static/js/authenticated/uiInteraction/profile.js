export const profile = async (userResponse) =>{
    const fullName = `${userResponse.firstName} ${userResponse.lastName}`;
    console.log(fullName);
    document.querySelector('#profile-full-name').textContent = `${fullName}`;
}