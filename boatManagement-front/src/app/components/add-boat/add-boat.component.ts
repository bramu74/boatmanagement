import { Component, OnInit } from '@angular/core';
import { BoatService } from 'src/app/services/boat.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-boat',
  templateUrl: './add-boat.component.html',
  styleUrls: ['./add-boat.component.css']
})
export class AddBoatComponent implements OnInit {
  addForm: FormGroup = new FormGroup({
      name: new FormControl(null, Validators.required),
      description: new FormControl(null, null) },
    null);

  submitted = false;
  errorMessage = '';

  constructor(private boatService: BoatService,
              private router: Router) { }

  ngOnInit(): void {
  }

  get control() { return this.addForm.controls; }

  addBoat(): void {
    this.submitted = true;
    if (this.addForm.invalid) {
      return;
    }

    this.boatService.create(this.addForm.value)
      .subscribe({
        next: (res) => {
          this.router.navigate(['/boats']);
        },
        error: (e) => {
          this.errorMessage = 'Error while adding boat';
        }
      });
  }
}
