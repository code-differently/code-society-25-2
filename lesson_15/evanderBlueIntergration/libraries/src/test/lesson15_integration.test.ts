import { Credit } from '../models/credit.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';
import { MediaType } from '../models/media_type.js';
import { Role } from '../models/role.js';
import { SearchCriteria } from '../models/search_criteria.js';

describe('MediaCollectionApp Integration', () => {
  let collection: MediaCollection;

  beforeEach(() => {
    collection = new MediaCollection();
    // Add sample items
    collection.addItem(
      new MediaItem('1', 'Inception', MediaType.Movie, 2010, [
        new Credit('1', 'Leonardo DiCaprio', Role.Actor),
      ]),
    );
    collection.addItem(
      new MediaItem('2', 'Interstellar', MediaType.Movie, 2014, [
        new Credit('2', 'Matthew McConaughey', Role.Actor),
      ]),
    );
    collection.addItem(
      new MediaItem('3', 'The Office', MediaType.TVShow, 2005, [
        new Credit('3', 'Steve Carell', Role.Actor),
      ]),
    );
  });

  it('should search by title', () => {
    const criteria: SearchCriteria = { title: 'Inception' };
    const results = collection.search(criteria);
    expect(Array.from(results).map((item) => item.getTitle())).toContain(
      'Inception',
    );
  });

  it('should search by release year', () => {
    const criteria: SearchCriteria = { releaseYear: 2014 };
    const results = collection.search(criteria);
    expect(Array.from(results).map((item) => item.getTitle())).toContain(
      'Interstellar',
    );
  });

  it('should search by cast name', () => {
    const criteria: SearchCriteria = { creditName: 'Steve Carell' };
    const results = collection.search(criteria);
    expect(Array.from(results).map((item) => item.getTitle())).toContain(
      'The Office',
    );
  });

  it('should return empty set for no match', () => {
    const criteria: SearchCriteria = { title: 'Nonexistent' };
    const results = collection.search(criteria);
    expect(results.size).toBe(0);
  });
});
