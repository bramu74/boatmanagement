import {Component, Input, OnInit} from '@angular/core';
import { BoatService } from 'src/app/services/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Boat } from 'src/app/models/boat.model';
import {FormControl, FormGroup, Validators} from "@angular/forms";
@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentBoat: Boat = {
    id:'',
    name:'',
    description:''
  };

  errorMessage = '';
  submitted = false;

  updateForm: FormGroup = new FormGroup({
      name: new FormControl(null, Validators.required),
      description: new FormControl(null, null) },
    null);

  constructor(
    private boatService: BoatService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.errorMessage = '';
      this.getBoat(this.route.snapshot.params["id"]);
    }
  }

  get control() { return this.updateForm.controls; }

  getBoat(id: string): void {
    this.boatService.get(id)
      .subscribe({
        next: (data) => {
          this.currentBoat.id = data.id;
          this.currentBoat.name = data.name;
          this.currentBoat.description = data.description;
          this.updateForm.controls['name'].setValue(data.name);
          this.updateForm.controls['description'].setValue(data.description);
        },
        error: (e) => {
          this.errorMessage = 'Error while getting boat';
        }
      });
  }

  updateBoat(): void {
    this.submitted = true;
    if (this.updateForm.invalid) {
      console.log("invalid")
      return;
    }
    this.errorMessage = '';
    this.boatService.update(this.currentBoat.id, this.updateForm.value)
      .subscribe({
        next: (res) => {
          this.router.navigate(['/boats']);
        },
        error: (e) => {
          this.errorMessage = 'Error while updating boat';
        }
      });
  }

  deleteBoat(): void {
    this.boatService.delete(this.currentBoat.id)
      .subscribe({
        next: (res) => {
          this.router.navigate(['/boats']);
        },
        error: (e) => {
          this.errorMessage = 'Error while deleting boat';
        }
      });
  }

}
