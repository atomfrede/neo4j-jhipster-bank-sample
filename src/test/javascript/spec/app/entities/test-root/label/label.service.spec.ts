import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LabelService } from 'app/entities/test-root/label/label.service';
import { ILabel, Label } from 'app/shared/model/test-root/label.model';

describe('Service Tests', () => {
  describe('Label Service', () => {
    let injector: TestBed;
    let service: LabelService;
    let httpMock: HttpTestingController;
    let elemDefault: ILabel;
    let expectedResult: ILabel | ILabel[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LabelService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Label('ID', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Label', () => {
        const returnedFromService = Object.assign(
          {
            labelName: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
