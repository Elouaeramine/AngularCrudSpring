import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Person } from './model/Person';
import { UsersService } from './users.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit {

  title = 'CRUD APP';
  constructor(
    private userService: UsersService
  ) { }

  allPersons : Person[] = [];

  ngOnInit() {
    this.getUsers();
  }


  getUsers(){
    this.userService.getAllData()
    .subscribe( data => {
      console.log(data)
      this.allPersons  = data ;
    }, 
    (err)=> {
      console.error(err)
    })
  }  


  
  submitData(value: Person) {
    let body : Person = {
      firstName: value.firstName,
      lastName : value.lastName,
      age: value.age
    }
    this.userService.postData(body)
    .subscribe(response => {
      console.log(response)
    })

    window.location.reload();
  }

  updateData(value: Person, id :number){
    let body : Person = {
      firstName: value.firstName,
      lastName : value.lastName,
      age: value.age
    }

    this.userService.updateData(body, id)
    .subscribe(response => {
      console.log(response);
    })
  }

  delete(id :number){
    this.userService.deleteData(id)
    .subscribe(response => {
      console.log(response);
    })

    window.location.reload();

  }
}
