async function isGalleryExistByURL(urlname)
{
    const result = await axios.get(`/galleryRest/urlname/${urlname}`)
    return result.data
}

async function isGalleryExistByTitle(title)
{
    const result = await axios.get(`/galleryRest/title/${title}`)
    return result.data
}