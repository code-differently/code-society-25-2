import { run } from "node:test";
import { MediaCollectionApp } from "./cli/media_collection_app.js";
import { Loaders } from "./loaders/loaders.module.js";
import { Test, TestingModule } from "@nestjs/testing";
import { Loader } from "./loaders/loader.js";
import { AppModule } from "./app.module.js";

describe('MediaCollectionTest',()=> {

    let moduleFixture: TestingModule;
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      let loaders: Loader[];
    
      beforeAll(async () => {
        moduleFixture = await Test.createTestingModule({
          imports: [AppModule],
        }).compile();
    
        loaders = moduleFixture.get(Loaders);
      });


      

























      







});