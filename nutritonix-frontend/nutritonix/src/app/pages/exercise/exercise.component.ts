import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FoodItemService } from '../../services/food-item.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css'],
  standalone: true,
  imports: [CommonModule,FormsModule,ReactiveFormsModule]
})
export class ExerciseComponent {
  exerciseForm: FormGroup;
  exercises: any[] = [];
  loading = false;

  constructor(private fb: FormBuilder, private nutritionService: FoodItemService) {
    this.exerciseForm = this.fb.group({
      query: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.exerciseForm.valid) {
      this.loading = true;
      const body = { query: this.exerciseForm.value.query };
      this.nutritionService.getExerciseInfo(body).subscribe({
        next: (res) => {
          this.exercises = res.exercises;
          this.loading = false;
        },
        error: (err) => {
          console.error('Error fetching exercise data:', err);
          this.loading = false;
        }
      });
    }
  }
}
