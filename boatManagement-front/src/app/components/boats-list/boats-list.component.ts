import { Component, OnInit } from '@angular/core';
import { Boat } from 'src/app/models/boat.model';
import { BoatService } from 'src/app/services/boat.service';

@Component({
  selector: 'app-boats-list',
  templateUrl: './boats-list.component.html',
  styleUrls: ['./boats-list.component.css']
})
export class BoatsListComponent implements OnInit {
  boats?: Boat[];
  currentBoat: Boat = {};
  currentIndex = -1;
  errorMessage = '';
  constructor(private boatService: BoatService) { }

  ngOnInit(): void {
    this.getBoats();
  }
  getBoats(): void {
    this.boatService.getAll()
      .subscribe({
        next: (data) => {
          this.boats = data;
          if(this.boats.length == 0) {
            this.errorMessage = 'No existing boats';
          }
        },
        error: (e) => {
          this.errorMessage = 'Error while getting boats';
        }
      });
  }
  refreshList(): void {
    this.getBoats();
    this.currentBoat = {};
    this.currentIndex = -1;
  }
  setActiveBoat(boat: Boat, index: number): void {
    this.currentBoat = boat;
    this.currentIndex = index;
  }

}
