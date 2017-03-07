$(document).ready(main);

function main() {

	/* Signup form validation */
	$('#signupForm').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			username : {
				validators : {
					notEmpty : {
						message : 'Email is required'
					},
					emailAddress : {
						message: 'The input is not a valid email address'
					}
				}
			},
			firstName: {
				validators: {
					notEmpty: {
						message: 'First name is required'
					}
				}
			},
			lastName: {
				validators: {
					notEmpty: {
						message: 'Last name is required'
					}
				}
			},
			password : {
				validators : {
					notEmpty : {
						message : 'Password is required'
					},
					identical : {
						field : 'confirmPassword',
						message : 'Password and its confirm are not the same'
					}
				}
			},
			confirmPassword : {
				validators : {
					notEmpty : {
						message : 'Confirmation password is required'
					},
					identical : {
						field : 'password',
						message : 'Password and its confirm are not the same'
					}
				}
			}
		}
	});

}