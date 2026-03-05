import { Component } from '@angular/core';
import { FoodItemService } from '../../services/food-item.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WishlistService } from '../../services/wishlist.service';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent {
  query: string = '';
  commonItems: any[] = [];
  brandedItems: any[] = [];
  loading: boolean = false;
  selectedFoodDetails: any = null;


  constructor(private foodItemService: FoodItemService,
    private wishlistService: WishlistService
  ) {}

  ngOnInit(): void {
    
  }

  onSearch(): void {
    if (this.query.trim() !== '') {
      this.loading = true;
      this.foodItemService.searchFood(this.query).subscribe(
        (response) => {
          this.commonItems = response.common || [];
          this.brandedItems = response.branded || [];
          this.loading = false;
        },
        (error) => {
          console.error('Error fetching food items', error);
          this.loading = false;
        }
      );
    } else {
      this.commonItems = [];
      this.brandedItems = [];
    }
  }

  onAddToWishlist(tagId: string): void {
    const userId = localStorage.getItem('userId');
    console.log(tagId);
    if (!userId) {
      alert('User not logged in!');
      return;
    }
  
    this.wishlistService.addToWishlist(userId, tagId,this.query).subscribe(
      (res) => {
        console.log('Added to wishlist:', res);
        alert('Item added to wishlist!');
      },
      (err) => {
        console.error('Wishlist error:', err);
        alert('Item already in wishlist!');
      }
    );
  }
  
}
