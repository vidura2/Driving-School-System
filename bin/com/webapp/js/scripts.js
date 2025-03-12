function validateForm() {
    let email = document.querySelector('input[name="email"]');
    if (email && !email.value.includes('@')) {
        alert('Please enter a valid email');
        return false;
    }
    return true;
}