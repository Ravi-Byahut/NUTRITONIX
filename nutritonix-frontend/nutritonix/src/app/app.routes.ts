import { Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { FoodListComponent } from './pages/food-list/food-list.component';
import { FavoritesComponent } from './pages/favorites/favorites.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { AuthGuard } from './auth.guard';
import { ExerciseComponent } from './pages/exercise/exercise.component';
import { AboutComponent } from './pages/about/about.component';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {path:'about',component:AboutComponent},
  { path: 'foods', component: FoodListComponent ,canActivate: [AuthGuard] },
  { path: 'favorites', component: FavoritesComponent,canActivate: [AuthGuard] },
  {path:'dashboard',component:DashboardComponent,canActivate: [AuthGuard]},
  {path:'wishlist',component:WishlistComponent,canActivate: [AuthGuard]},
  {path:'exercise',component:ExerciseComponent,canActivate: [AuthGuard]},
];
