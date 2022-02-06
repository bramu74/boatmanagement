import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({
    login: new FormControl(null, Validators.required),
    password: new FormControl(null, Validators.required)},
    null);

  failed = false;
  submitted = false;
  errorMessage = '';

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  get control() { return this.loginForm.controls; }

  login(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    const data = {
      login: this.loginForm.controls["login"].value,
      password: this.loginForm.controls["password"].value
    };
    this.authService.login(data)
      .subscribe({
        next: (res) => {
            this.failed = false;
            this.router.navigate(['/boats']);
        },
        error: (e) => {
          this.failed = true;
          this.errorMessage = "Login failed";
        }
      });
  }
  logout(): void {
    this.authService.logout()
      .subscribe({
      });
  }
}
