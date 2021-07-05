import Vue from 'vue';

Vue.filter('truncate', (text, length, suffix) => {
  if (text.length > length) {
    return text.substring(0, length) + suffix;
  } else {
    return text;
  }
});

Vue.filter('spaceCutter', (text) => {
  let cnt = 0;
  let str = '';
  for (let i = 0; i < text.length; i++) {
    if (text.charAt(i) === ' ') {
      if (cnt > 2) {
        str += '<br>';
        cnt = 0;
      } else str += ' ';
      continue;
    } else {
      str += text.charAt(i);
      cnt++;
    }
  }
  return str;
});
