import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILocationType, LocationType } from 'app/shared/model/location-type.model';
import { LocationTypeService } from './location-type.service';

@Component({
  selector: 'jhi-location-type-update',
  templateUrl: './location-type-update.component.html'
})
export class LocationTypeUpdateComponent implements OnInit {
  locationType: ILocationType;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(protected locationTypeService: LocationTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ locationType }) => {
      this.updateForm(locationType);
      this.locationType = locationType;
    });
  }

  updateForm(locationType: ILocationType) {
    this.editForm.patchValue({
      id: locationType.id,
      name: locationType.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const locationType = this.createFromForm();
    if (locationType.id !== undefined) {
      this.subscribeToSaveResponse(this.locationTypeService.update(locationType));
    } else {
      this.subscribeToSaveResponse(this.locationTypeService.create(locationType));
    }
  }

  private createFromForm(): ILocationType {
    const entity = {
      ...new LocationType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocationType>>) {
    result.subscribe((res: HttpResponse<ILocationType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
