import React, { useState, useRef, useEffect } from 'react';
import { motion } from "framer-motion"
import clsx from 'clsx';
import { ArrowPathIcon, CheckIcon } from '@heroicons/react/20/solid';
import axios from "axios";
const Shortener = () => {
  const [longUrl, setLongUrl] = useState("");
  const [shortenedUrl, setShortenedUrl] = useState(undefined);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(undefined);
  const contentRef = useRef(null);
  const justSucceeded = Boolean(shortenedUrl) && !loading && !error;

  const onChange = (e) => {
    setLongUrl(e.target.value);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setShortenedUrl(undefined);
    setError(undefined);
    const { data } = await axios.post("http://localhost:4000/url/shorten", { longUrl });
    setLoading(false);
    setShortenedUrl(data.shortURL);
  }

  const copy = async () => {
		if (shortenedUrl) {
			try {
				await navigator.clipboard.writeText("localhost:4000/"+shortenedUrl);
			} catch {}
		}
	};

  useEffect(() => {
    contentRef.current.style.height = `calc(${window.innerHeight}px - 160px)`;
  }, []);

  return (
    <div className="bg-black py-20" ref={contentRef}>
      <div className="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden flex justify-center md:max-w-4xl">
        <div className="md:flex w-full">
          <div className="p-8 w-full">
            <div className="w-4/5 uppercase tracking-wide text-xl mx-auto ">
              Paste URL here to shorten...
            </div>
            <div className="mx-auto w-4/5 mt-7 flex">
              <form className='flex h-14 w-full rounded bg-white' onSubmit={handleSubmit} >
                <input
                  className='placeholder-[rgba(10, 0, 37, 0.6)] ring-1 ring-inset ring-gray-300 h-full w-full rounded-l bg-transparent p-4 text-black outline-none'
                  placeholder='https://google.com'
                  type='url'
                  name='url'
                  value={longUrl}
                  onChange={onChange}
                  required={true}
                  autoFocus={true}
                />
                <motion.button
                  className={clsx('flex h-full ring-1 ring-inset ring-gray-300 min-w-max items-center justify-center rounded-r p-4 transition-colors', {
                    'font-bold text-zws-purple-500 hover:bg-purple-100 active:bg-purple-200 disabled:bg-purple-200': !(
                      justSucceeded || error
                    ),
                    'bg-green-400 text-zws-purple-900': justSucceeded,
                    'bg-red-500 text-white': error,
                  })}
                  disabled={loading}
                  type='submit'
                  layout={true}
                >
                  {loading && <ArrowPathIcon className='h-6 w-6 animate-spin opacity-50' />}
                  {!(loading || justSucceeded || error) && 'Shorten'}
                  {error}
                  {justSucceeded && <CheckIcon className='h-6 w-6' />}
                </motion.button>
              </form>
            </div>
            <div className='pt-4 text-center'>
              {shortenedUrl && !loading ? (
                <button
                  type='button'
                  className='text-zws-purple-100 underline decoration-dotted underline-offset-2 transition-opacity hover:opacity-80 active:opacity-60'
                  onClick={copy}
                >
                  {shortenedUrl} (click to copy)
                </button>
              ) : (
                // Used to prevent layout shift when hiding the button
                <div className='h-6' />
              )}
            </div>
          </div>
        </div>
      </div>

    </div>
  )
}

export default Shortener;