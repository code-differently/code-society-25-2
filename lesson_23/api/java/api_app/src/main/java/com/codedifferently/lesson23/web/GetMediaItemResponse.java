package com.codedifferently.lesson23.web;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetMediaItemResponse {
  MediaItemResponse item;
}
