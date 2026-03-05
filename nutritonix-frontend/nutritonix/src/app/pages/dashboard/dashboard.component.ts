import { Component } from '@angular/core';
import { UserCredential } from '../../model/user';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  imports: [CommonModule,RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  user: UserCredential | null = null;
  errorMessage: string = '';

  constructor(private userService: UserService,private router:Router) { }

  ngOnInit(): void {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');
    
    if (userId && token) {
      this.fetchUserData(Number(userId), token);
    } else {
      this.errorMessage = 'User not logged in or token is missing.';
    }
  }
  
  fetchUserData(userId: number, token: string): void {
    this.userService.getUserById(userId, token).subscribe(
      (data: UserCredential) => {
        this.user = data;
        console.log((data));
      },
      error => {
        this.errorMessage = 'Error fetching user data';
        console.error(error);
      }
    );
}
onExploreClick(): void {
  this.router.navigate(['/foods']);
}
}
