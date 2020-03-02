import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SampleNeo4JNoCacheTestModule } from '../../../../test.module';
import { LabelDetailComponent } from 'app/entities/test-root/label/label-detail.component';
import { Label } from 'app/shared/model/test-root/label.model';

describe('Component Tests', () => {
  describe('Label Management Detail Component', () => {
    let comp: LabelDetailComponent;
    let fixture: ComponentFixture<LabelDetailComponent>;
    const route = ({ data: of({ label: new Label('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SampleNeo4JNoCacheTestModule],
        declarations: [LabelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LabelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LabelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load label on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.label).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
