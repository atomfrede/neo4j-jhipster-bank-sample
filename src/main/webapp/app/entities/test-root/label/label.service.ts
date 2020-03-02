import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILabel } from 'app/shared/model/test-root/label.model';

type EntityResponseType = HttpResponse<ILabel>;
type EntityArrayResponseType = HttpResponse<ILabel[]>;

@Injectable({ providedIn: 'root' })
export class LabelService {
  public resourceUrl = SERVER_API_URL + 'api/labels';

  constructor(protected http: HttpClient) {}

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ILabel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILabel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
