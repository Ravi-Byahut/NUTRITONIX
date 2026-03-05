import { Component, OnInit } from '@angular/core';
import { WishlistService } from '../../services/wishlist.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css'],
  imports: [CommonModule, FormsModule]
})
export class WishlistComponent implements OnInit {
  wishlistItems: any[] = [];

  constructor(private wishlistService: WishlistService) {}

  ngOnInit(): void {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.wishlistService.getWishlist(userId).subscribe(
        (data) => {
          this.wishlistItems = data;
        },
        (error) => {
          console.error('Error fetching wishlist', error);
        }
      );
    }
  }

  removeItem(tagId: string): void {
    const userId = localStorage.getItem('userId');
    if (!userId) return;

    this.wishlistService.removeFromWishlist(userId, tagId).subscribe(
      () => {
        this.wishlistItems = this.wishlistItems.filter(item => item.tagId !== tagId);
        alert("Item removed from wishlist successfully!");
      },
      (error) => {
        console.error('Error removing item from wishlist', error);
      }
    );
  }
}
